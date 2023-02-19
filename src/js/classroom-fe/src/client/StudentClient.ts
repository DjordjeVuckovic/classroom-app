import {baseUrl} from "../urls";
import axios from "axios";
// const api = axios.create({
//     baseURL: `${baseUrl}`,
//     headers: {
//         'Content-Type': "application/json"
//     }
// })
//const getStudentsUrl = `${baseUrl}/students`
export function getAllStudents(){
    return axios.get(`${baseUrl}/students`).then(
        value => {
            return value.data
        }
    )
        .catch(reason => {
            console.log(reason)
        })
}
