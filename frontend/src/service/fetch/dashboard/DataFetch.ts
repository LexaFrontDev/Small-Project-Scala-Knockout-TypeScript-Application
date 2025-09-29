
import {UsersList} from "../../../props/dashboard/UsersList";


export class DataFetch {

    async  fetchData(): Promise<UsersList | boolean>{
       const response = await fetch(`http://localhost:9000/users`, {
           method: 'GET',
           headers: {
               'Content-Type': 'application/json',
           }
       });

       if(!response.ok){
            return false;
        }
        return response.json()
   }


}