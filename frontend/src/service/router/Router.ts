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

    navigate(path: string) {
        window.location.hash = path;
    }

    private handleRouteChange = () => {
        const path = window.location.hash.replace("#", "") || "/";
        const ComponentClass = this.routes[path] || this.routes["/"];
        if (!ComponentClass) return;

        const root = document.getElementById("root");
        if (!root) return;
        root.innerHTML = "";
        this.currentInstance = new ComponentClass();
    };
}
