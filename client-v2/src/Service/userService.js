import axios from 'axios'

const BASE_URL = "http://localhost:8091/api/employees"


const config = {
    headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken")
    }

}

const getEmployee = () => {
    return axios.get(BASE_URL, config);
}

const saveEmployee = (employee) =>  {
    return axios.post(BASE_URL + "/" , {
        headers: {
            'Authorization': "Bearer " + localStorage.getItem("accessToken")
        },
        body: employee
    })
}

const updateEmployee = (employee,  employeeId) =>  {
    return axios.put(BASE_URL + "/" + employeeId, employee, {

            'Authorization': "Bearer " + localStorage.getItem("accessToken"),

        body: JSON.stringify(employee)

    } )
}

const getEmployeeById = (employeeId) =>  {
    return axios.post(BASE_URL + "/" + employeeId, null, config)
}
const getEmployeeByCode = (code) =>  {
    return axios.post(BASE_URL + "/getByCode/" + code, null, config)
}

const deleteEmployee = (employeeId) =>  {
    return axios.delete(BASE_URL + "/" + employeeId, config)
}

const checkCode  = (id, code) => {
    const config1 = {
        params: {id: id, code: code},
        headers: {
            'Authorization': "Bearer " + localStorage.getItem("accessToken")
        }
    };
    var url = BASE_URL + "/checkCode";
    return axios.get(url, config1);
}

export const GetEmployee = getEmployee
export const SaveEmployee = saveEmployee
export const UpdateEmployee = updateEmployee
export const GetEmployeeById = getEmployeeById
export const GetEmployeeByCode = getEmployeeByCode
export const DeleteEmployee = deleteEmployee
export const CheckCode = checkCode