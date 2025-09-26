
import {UsersList} from "../../../props/dashboard/UsersList";


export class DataFetch {


    async  fetchData(): Promise<UsersList | boolean>{
       const response = await fetch('https://small-project-scala-knockout-typescript-62bw.onrender.com/users', {
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