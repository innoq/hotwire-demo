import { Controller } from "stimulus";
import { connectStreamSource, disconnectStreamSource } from "@hotwired/turbo";

export default class ConnectWebsocketController extends Controller {
    static targets = ["toggle"]
    static values = { connected: Boolean }
    
    eventSource = undefined;
    
    toggleStream() {
        if (this.connectedValue) {
            disconnectStreamSource(this.eventSource);
            this.eventSource.close();
            this.eventSource = undefined;
            this.connectedValue = false;
            this.toggleTarget.innerText = 'Attach WS Stream';
        }
        else {
            this.eventSource = new WebSocket("ws://localhost:8080/stream-updates");
            connectStreamSource(this.eventSource);
            this.connectedValue = true;
            this.toggleTarget.innerText = 'Detach WS Stream';
        }
    }
}
