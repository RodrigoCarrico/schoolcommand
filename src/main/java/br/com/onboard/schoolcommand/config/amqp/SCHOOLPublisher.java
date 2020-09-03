package br.com.onboard.schoolcommand.config.amqp;

import br.com.onboard.schoolcommand.utils.DomainCommandEvents;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;


@AllArgsConstructor
@EnableBinding(SCHOOLChannel.SchoolExchangeOutput.class)
public class SCHOOLPublisher<T extends DomainCommandEvents> {

    private SCHOOLChannel.SchoolExchangeOutput schoolExchangeOutput;

    public void dispatch(T object) {
        for (var event : object.getEvents()) {
            schoolExchangeOutput.output().send(
                    MessageBuilder.withPayload(event)
                            .setHeader("type", event.getClass().getSimpleName())
                            .build());
        }
        object.clean();
    }
}
