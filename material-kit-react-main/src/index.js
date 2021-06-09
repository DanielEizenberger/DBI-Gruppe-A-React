import React from "react";
import ReactDOM from "react-dom";

import { createBrowserHistory } from "history";
import { Router, Route, Switch } from "react-router-dom";
import 'bootstrap/dist/css/bootstrap.min.css';



// pages for this product
import Components from "views/Components/Components.js";
import newService from "./views/Components/newService";
import showEmployees from "./views/Components/showEmployees";
import newEmployees from "./views/Components/newEmployees";
var hist = createBrowserHistory();

ReactDOM.render(
  <Router history={hist}>
    <Switch>
      <Route path="/createService" component={newService} />
      <Route path="/newEmployee" component={newEmployees}/>
      <Route path="/showEmployees" component={showEmployees}/>
      <Route path="/" component={Components} />



    </Switch>
  </Router>,
  document.getElementById("root")
);
