import * as ko from "knockout";
import { DataFetch } from "../../service/fetch/dashboard/DataFetch";
import { UsersList } from "../../props/dashboard/UsersList";

const dataFetch = new DataFetch();

export class DashboardPage {
    usersList = ko.observableArray<UsersList>([]);

    constructor() {
        this.getData();
        this.render();
    }

    getData = async () => {
        const result = await dataFetch.fetchData();
        if (!result) {
            this.usersList([]);
            return false;
        }
        // @ts-ignore
        this.usersList(result);
    }

    render() {
        const root = document.getElementById("root");
        if (!root) return;

        root.innerHTML = `
            <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4 shadow-sm">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Dashboard</a>
                    <div class="d-flex gap-2">
                        <button id="refresh-data" class="btn btn-success">Обновить данные</button>
                        <button id="go-home" class="btn btn-primary">Home</button>
                    </div>
                </div>
            </nav>

            <div class="container">
                <h1 class="mb-4">Users List</h1>
                <div class="table-responsive shadow-sm">
                    <table class="table table-striped table-hover table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Subscribed</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody data-bind="foreach: usersList">
                            <tr>
                                <td data-bind="text: name"></td>
                                <td data-bind="text: email"></td>
                                <td data-bind="text: subscribe"></td>
                                <td data-bind="text: role"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        `;


        const homeBtn = document.getElementById("go-home");
        if (homeBtn) {
            homeBtn.addEventListener("click", () => {
                window.location.hash = "/";
            });
        }


        const refreshBtn = document.getElementById("refresh-data");
        if (refreshBtn) {
            refreshBtn.addEventListener("click", () => {
                this.getData();
            });
        }
        ko.cleanNode(root);
        ko.applyBindings(this, root);
    }
}
