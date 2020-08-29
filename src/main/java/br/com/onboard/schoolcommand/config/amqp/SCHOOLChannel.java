package br.com.onboard.schoolcommand.config.amqp;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SCHOOLChannel {

    public static final String SCHOOL_OUTPUT = "school-output-events";

    public interface SchoolExchangeOutput {
        @Output(SCHOOLChannel.SCHOOL_OUTPUT)
        MessageChannel output();
    }
}
