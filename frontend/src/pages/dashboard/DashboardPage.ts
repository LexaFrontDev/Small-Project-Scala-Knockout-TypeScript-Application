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
        this.usersList(result);
    }

    render() {
        const appDiv = document.createElement("div");
        appDiv.id = "src";
        document.body.appendChild(appDiv);

        appDiv.innerHTML = `
            <h1 class="text-2xl font-bold mb-4">Dashboard - Users</h1>
            <table class="min-w-full border border-gray-300">
                <thead>
                    <tr class="bg-gray-100">
                        <th class="px-4 py-2 border">Name</th>
                        <th class="px-4 py-2 border">Email</th>
                        <th class="px-4 py-2 border">Subscribed</th>
                        <th class="px-4 py-2 border">Role</th>
                    </tr>
                </thead>
                <tbody data-bind="foreach: usersList">
                    <tr class="hover:bg-gray-50">
                        <td class="px-4 py-2 border" data-bind="text: name"></td>
                        <td class="px-4 py-2 border" data-bind="text: email"></td>
                        <td class="px-4 py-2 border" data-bind="text: subscribe"></td>
                        <td class="px-4 py-2 border" data-bind="text: role"></td>
                    </tr>
                </tbody>
            </table>
        `;

        ko.applyBindings(this, appDiv);
    }
}
