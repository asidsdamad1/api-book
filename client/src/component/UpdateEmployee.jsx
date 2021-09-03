import React, {Component} from 'react';
import EmployeeService from "../services/EmployeeService";

class CreateEmployee extends Component {
    constructor(props) {
        super(props);

        this.state = {
            name: '',
            code: '',
            email: '',
            age: '',
            phone: ''

        }
        this.changeCodeHandel = this.changeCodeHandel.bind(this);
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changePhoneHandler = this.changePhoneHandler.bind(this);
        this.changeAgeHandler = this.changeAgeHandler.bind(this);
    }

    saveOrUpdate = (e) => {
        e.preventDefault();

        let employee = {
            name: this.state.name,
            code: this.state.code,
            email: this.state.email,
            age: this.state.age,
            phone: this.state.phone,
        }
        console.log('employee => ' + JSON.stringify(employee));
        console.log('id => ' + JSON.stringify(this.state.id));
        EmployeeService.saveOrUpdateEmployee(employee, this.state.id).then( res => {
            this.props.history.push('/employees');
        });
    }
    changeNameHandler = (event) => {
        this.setState({phonee: event.target.value});
    }
    changeCodeHandel = (event) => {
        this.setState({code: event.target.value});
    }
    changeEmailHandler = (event) => {
        this.setState({email: event.target.value});
    }
    changePhoneHandler = (event) => {
        this.setState({phone: event.target.value});
    }
    changeAgeHandler = (event) => {
        this.setState({age: event.target.value});
    }

    cancel() {
        this.props.history.push('/employees');
    }
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-center">Add Employee</h3>
                            <div className="card-body">
                                <form action="">
                                    <div className="form-group">
                                        <label>Name: </label>
                                        <input placeholder={"Name"} name={"name"} className={"form-control"}
                                               value={this.state.name} onChange={this.changeNameHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Code: </label>
                                        <input placeholder={"Code"} name={"code"} className={"form-control"}
                                               value={this.state.code} onChange={this.changeCodeHandel}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Email: </label>
                                        <input type={"email"} placeholder={"Email"} name={"email"} className={"form-control"}
                                               value={this.state.email} onChange={this.changeEmailHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Age: </label>
                                        <input placeholder={"Age"} name={"age"} className={"form-control"}
                                               value={this.state.age} onChange={this.changeAgeHandler}/>
                                    </div>
                                    <div className="form-group">
                                        <label>Phone: </label>
                                        <input placeholder={"Phone"} name={"phone"} className={"form-control"}
                                               value={this.state.phone} onChange={this.changePhoneHandler}/>
                                    </div>

                                    <button className={"btn btn-success"} onClick={this.saveOrUpdate}>Save</button>
                                    <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        );
    }
}

export default CreateEmployee;