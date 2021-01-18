import { Controller } from "stimulus";
import { connectStreamSource, disconnectStreamSource } from "@hotwired/turbo";

export default class ConnectSSEController extends Controller {
    static targets = ["toggle"]
    
    connected = false;
    eventSource = undefined;
    
    toggle() {
        if (this.connected) {
            disconnectStreamSource(this.eventSource);
            this.eventSource.close();
            this.eventSource = undefined;
            this.connected = false;
            this.toggleTarget.innerText = 'Attach SSE Stream';
        }
        else {
            this.eventSource = new EventSource("http://localhost:8080/turbo-sse");
            connectStreamSource(this.eventSource);
            this.connected = true;
            this.toggleTarget.innerText = 'Detach SSE Stream';
        }
    }
}
