import {baseUrl} from "../urls";
import axios from "axios";
export function getAllStudents(){
    return axios.get(`${baseUrl}/students`)
}
export function createStudent(student) {
    return axios.post(`${baseUrl}/students`, student)
}
