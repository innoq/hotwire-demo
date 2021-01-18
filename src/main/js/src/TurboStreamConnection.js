import { connectStreamSource, disconnectStreamSource } from "@hotwired/turbo";

class TurboStreamConnection extends HTMLElement {
    get src() {
        return this.getAttribute("src");
    }

    set src(value) {
        if (value) {
            this.setAttribute("src", value);
        } else {
            this.removeAttribute("src");
        }
    }

    connectedCallback() {
        connectStreamSource(this);
        this.ws = this.connectWebSocket();
    }

    disconnectedCallback() {
        disconnectStreamSource(this);
        if (this.ws) {
            this.ws.close();
            this.ws = null;
        }
    }

    /**
     * Called in response to a websocket message. Unpacks the websocket message
     * and dispatches it as a new MessageEvent to Turbo Streams.
     * 
     * @param {MessageEvent} messageEvent The original message to dispatch
     */
    dispatchMessageEvent(messageEvent) {
        const event = new MessageEvent("message", { data: messageEvent.data });
        this.dispatchEvent(event);
    }

    connectWebSocket() {
        const socketLocation = `ws://${window.location.host}${this.src}`;
        const ws = new WebSocket(socketLocation);
        ws.onmessage = msg => this.dispatchMessageEvent(msg);
        return ws;
    }
}

customElements.define("turbo-stream-connection", TurboStreamConnection);
