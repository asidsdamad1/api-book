import axios from 'axios'

const BASE_URL = "/api/employees"

class EmployeeService {
    getEmployee() {
        return axios.get(BASE_URL);
    }

    saveOrUpdateEmployee(employee, employeeId) {
        return axios.put(BASE_URL + "/" + employeeId, employee)
    }

    getEmployeeById(employeeId) {
        return axios.post(BASE_URL + "/" + employeeId)
    }
    getEmployeeByCode(code) {
        return axios.post(BASE_URL + "/getByCode/" + code)
    }

    deleteEmployee(employeeId) {
        return axios.delete(BASE_URL + "/" + employeeId)
    }

    checkCode(id, code) {
        const config = {params: {id: id, code: code}};
        var url = BASE_URL + "/checkCode";
        return axios.get(url, config);
    }
}

export default new EmployeeService()