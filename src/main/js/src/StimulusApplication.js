import { Application } from "stimulus";

import ConnectSSEController from "./ConnectSSEController.js";
import ConnectWebsocketController from "./ConnectWebsocketController.js";

const application = Application.start();
application.register('connect-websocket', ConnectWebsocketController);
application.register('connect-sse', ConnectSSEController);
console.log("Simulus controllers registered");
