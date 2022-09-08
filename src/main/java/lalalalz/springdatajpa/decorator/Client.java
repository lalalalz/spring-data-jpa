package lalalalz.springdatajpa.decorator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    private final Component component;

    public Client(Component component) {
        this.component = component;
    }

    public void execute() {
        String result = component.operation();
        log.info("Client execute result = {}", result);
    }
}
