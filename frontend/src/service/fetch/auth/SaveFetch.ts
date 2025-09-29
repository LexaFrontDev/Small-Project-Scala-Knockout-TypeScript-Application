import { RegisterFetchProps } from "../../../props/auth/register/RegisterFetchProps";

export class SaveFetch {


    async save(data: RegisterFetchProps): Promise<boolean> {
        const response = await fetch(`http://localhost:9000/users/save`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data)
        });

        return response.ok;
    }
}
