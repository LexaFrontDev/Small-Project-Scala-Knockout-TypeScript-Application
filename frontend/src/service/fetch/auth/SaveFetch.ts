import {RegisterFetchProps} from "../../../props/auth/register/RegisterFetchProps";

export class SaveFetch {

    async save(data: RegisterFetchProps): Promise<boolean> {
        const response = await fetch('https://small-project-scala-knockout-typescript-62bw.onrender.com/users/save', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });

        return response.ok;
    }


}