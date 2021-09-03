import axios from 'axios'
import { v4 as uuidv4 } from 'uuid';
uuidv4();
const BASE_URL = "http://localhost:8089/api/employees"

class EmployeeService {
    getEmployee() {
        return axios.get('')
    }


    saveEmployee(employee) {
        return axios.post('', employee)
    }

    updateEmployee(employee, employeeId) {
        return axios.put('/' + employeeId, employee)
    }

    getEmployeeById(employeeId) {
        return axios.post('/' + employeeId)
    }
    getEmployeeByCode(code) {
        return axios.post('/getByCode/' + code)
    }

    deleteEmployee(employeeId) {
        return axios.delete('/' + employeeId)
    }

    checkCode(id, code) {
        const config = {params: {id: id, code: code}};
        var url = "/checkCode";
        return axios.get(url, config);
    }
}

export default new EmployeeService()