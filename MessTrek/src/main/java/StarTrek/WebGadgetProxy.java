package StarTrek;

import Untouchables.WebGadget;

public class WebGadgetProxy {

    private final WebGadget delegate;

    public WebGadgetProxy(WebGadget delegate) {
        this.delegate = delegate;
    }

    public String parameter(String parameterName) {
        return delegate.parameter(parameterName);
    }

    public Object variable(String variableName) {
        return delegate.variable(variableName);
    }

    public void writeLine(String message) {
        delegate.writeLine(message);
    }

}
