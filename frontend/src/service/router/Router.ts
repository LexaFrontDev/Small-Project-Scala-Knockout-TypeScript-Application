import * as ko from "knockout";

export class Router {
    private routes: Record<string, any> = {};
    private currentInstance: any = null;

    constructor() {
        window.addEventListener("hashchange", this.handleRouteChange);
        window.addEventListener("load", this.handleRouteChange);
    }

    register(path: string, componentClass: any) {
        this.routes[path] = componentClass;
    }

    private handleRouteChange = () => {
        const path = window.location.hash.replace("#", "") || "home";

        const ComponentClass = this.routes[path];

        if (!ComponentClass) {
            console.warn(`Route "${path}" not found`);
            return;
        }


        if (this.currentInstance) {
            const oldDiv = document.getElementById("src");
            if (oldDiv) document.body.removeChild(oldDiv);
        }


        this.currentInstance = new ComponentClass();
    };
}
