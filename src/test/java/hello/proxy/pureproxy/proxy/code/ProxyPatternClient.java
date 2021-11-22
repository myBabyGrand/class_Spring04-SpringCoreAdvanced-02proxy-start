package hello.proxy.pureproxy.proxy.code;

public class ProxyPatternClient {

    public ProxyPatternClient(Subject subject) {
        this.subject = subject;
    }

    private Subject subject;

    public void execute(){
        subject.operation();
    }
}
