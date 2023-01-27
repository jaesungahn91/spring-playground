package io.github.js.service.global.exception;

import com.slack.api.Slack;
import com.slack.api.model.Attachment;
import com.slack.api.webhook.Payload;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackField;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.slack.api.model.Attachments.asAttachments;
import static com.slack.api.model.block.Blocks.*;
import static com.slack.api.model.block.composition.BlockCompositions.*;

@Service
public class SlackService {

    @Value("${spring.profiles.active}")
    private String activeProfile;
    @Value("${logging.slack.webhook-uri}")
    private String webhookUri;

    public void sendErrorAlarmBySlackApi(SlackErrorAlarmParam param) throws IOException {
        Slack slack = Slack.getInstance();
        Payload payload = Payload.builder()
                .attachments(
                        asAttachments(
                                Attachment.builder()
                                        .color("#DE3131")
                                        .blocks(asBlocks(
                                                header(header -> header.text(plainText("ERROR ALARM"))),
                                                section(section -> section.text(markdownText(param.getThrowable().getMessage()))),
                                                section(section -> section.text(markdownText("```" + param.getThrowable().getStackTrace()[0].toString() + "```"))),
                                                divider(),
                                                section(section -> section
                                                        .fields(
                                                                asSectionFields(
                                                                        markdownText("*Module*"),
                                                                        markdownText("*Active Profile*"),
                                                                        markdownText("`" + param.getMethodName() + "`"),
                                                                        markdownText("`" + activeProfile + "`"),
                                                                        markdownText("*Class*"),
                                                                        markdownText("*Method*"),
                                                                        markdownText("`" + param.getClassName() + "`"),
                                                                        markdownText("`" + param.getClassName() + "`")
                                                                )
                                                        )
                                                )
                                        )).build()
                        )
                ).build();
        slack.send(webhookUri, payload);
    }

    public void sendErrorAlarmBySlackWebhook(SlackErrorAlarmParam param) {
        SlackApi slackApi = new SlackApi(webhookUri);

        SlackAttachment slackAttachment = new SlackAttachment();
        slackAttachment.setFallback("ERROR");
        slackAttachment.setTitle(param.getThrowable().getMessage());
        slackAttachment.setText("```" + Arrays.toString(param.getThrowable().getStackTrace()) + "```");
//        slackAttachment.setText("```" + e.getStackTrace()[0].toString() + "```");
        slackAttachment.setColor("#DE3131");
        slackAttachment.setFields(
                List.of(
                        new SlackField().setTitle("*Module*").setValue("`" + param.getModuleName() + "`").setShorten(true),
                        new SlackField().setTitle("*Active Profile*").setValue("`" + activeProfile + "`").setShorten(true),
                        new SlackField().setTitle("*Class*").setValue("`" + param.getClassName() + "`").setShorten(true),
                        new SlackField().setTitle("*Method*").setValue("`" + param.getMethodName() + "`").setShorten(true)
                )
        );

        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setText("*Error Detected*");
        slackMessage.setUsername(param.getModuleName());
        slackMessage.setAttachments(Collections.singletonList(slackAttachment));

        slackApi.call(slackMessage);
    }

}