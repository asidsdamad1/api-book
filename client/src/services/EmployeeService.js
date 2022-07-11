import axios from 'axios'

const BASE_URL = "http://localhost:8091/api/employees"


const config = {
    headers: {
        'Authorization': "Bearer " + localStorage.getItem("accessToken")
    }
}

class EmployeeService {
    getEmployee() {
        return axios.get(BASE_URL, config);
    }

    saveEmployee(employee, employeeId) {
        return axios.post(BASE_URL + "/" + employeeId, employee, config)
    }

    updateEmployee(employee, employeeId) {
        return axios.put(BASE_URL + "/" + employeeId, employee, config)
    }

    getEmployeeById(employeeId) {
        return axios.post(BASE_URL + "/" + employeeId, null, config)
    }
    getEmployeeByCode(code) {
        return axios.post(BASE_URL + "/getByCode/" + code,null,  config)
    }

    deleteEmployee(employeeId) {
        return axios.delete(BASE_URL + "/" + employeeId, config)
    }

    checkCode(id, code) {
        const config1 = {params: {id: id, code: code},
            headers: {
                'Authorization': "Bearer " + localStorage.getItem("accessToken")
            }};
        var url = BASE_URL + "/checkCode";
        return axios.get(url, config1);
    }

}

export default new EmployeeService()