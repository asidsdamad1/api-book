
import React, {Component} from 'react';
import EmployeeService from "../../services/EmployeeService";


class ListEmployee extends Component {

    constructor(props) {
        super(props);

        this.state = {
            employees: []
        }
        this.addEmployee = this.addEmployee.bind(this);
        this.editEmployee = this.editEmployee.bind(this);
        this.deleteEmployee = this.deleteEmployee.bind(this);
    }


    addEmployee(){
        this.props.history.push('/add-employee/ ');
    }


    editEmployee(id){
        this.props.history.push(`/add-employee/${id}`);
    }
    deleteEmployee(id){
        EmployeeService.deleteEmployee(id).then( res => {
            this.setState({employees: this.state.employees.filter(employee => employee.id !== id)});
        });
    }
    viewEmployee(id){
        this.props.history.push(`/view-employee/${id}`);
    }
    componentDidMount() {
        EmployeeService.getEmployee().then((res) => {
            this.setState({ employees: res.data })
        })
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Employee List</h2>
                <nav className="navbar navbar-light bg-light" style={{alignItems: "center", justifyContent: "center"}}>
                    <form className="form-inline" style={{width: "50%"}}>
                        <input className="form-control mr-sm-2 ml-10 " style={{width: "85%"}} type="search" placeholder="Search" aria-label="Search"/>
                            <button style={{float:"right"}} className="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                    </form>
                </nav>
                <div className = "">
                    <button className="btn btn-primary" onClick={this.addEmployee}> Add Employee</button>
                </div>

                <br/>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <td> Code </td>
                                <td> Name </td>
                                <td> Age </td>
                                <td> Email </td>
                                <td> Phone </td>
                                <td> Actions </td>
                            </tr>
                        </thead>

                        <tbody>
                        {
                            this.state.employees.map(
                                (employee) =>
                                    <tr key = {employee.id}>
                                        <td>{employee.code}</td>
                                        <td>{employee.name}</td>
                                        <td>{employee.age}</td>
                                        <td>{employee.email}</td>
                                        <td>{employee.phone}</td>
                                        <td>
                                            <button onClick={ () => this.editEmployee(employee.id)} className="btn btn-info">Update </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () => this.deleteEmployee(employee.id)} className="btn btn-danger">Delete </button>
                                            <button style={{marginLeft: "10px"}} onClick={ () => this.viewEmployee(employee.id)} className="btn btn-info">View </button>
                                        </td>
                                    </tr>
                            )
                        }
                        </tbody>


                    </table>

                </div>
            </div>
        );  
    }
}

export default ListEmployee;