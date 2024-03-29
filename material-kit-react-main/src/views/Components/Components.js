import React from "react";
// nodejs library that concatenates classes
import classNames from "classnames";
// react components for routing our app without refresh;
// @material-ui/core components
import { makeStyles } from "@material-ui/core/styles";
// @material-ui/icons
// core components
import Header from "components/Header/Header.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";
import Parallax from "components/Parallax/Parallax.js";
import ServiceAddButton from "components/CustomButtons/ServiceAddButton";
import OpenEmployeesButton from "components/CustomButtons/OpenEmployeesButton";

// sections for this page
import SectionTable from "./Sections/SectionTable.js";
import styles from "assets/jss/material-kit-react/views/components.js";
//import SectionSearch from "./Sections/SectionSearch";
//import SectionFooter from "./Sections/SectionFooter";
import ownStyles from '../../assets/css/myOwnStyle.module.css'

import {Link} from "react-router-dom";

const useStyles = makeStyles(styles);

export default function Components(props) {



  const classes = useStyles();
  const { ...rest } = props;
  return (
    <div>
      <Header
        brand="Service Application"
        fixed
        color="transparent"
        changeColorOnScroll={{
          height: 400,
          color: "white",
        }}
        {...rest}
      />

      <Parallax image={require("assets/img/boat-5889919.png").default}>
        <div className={classes.container}>
          <GridContainer>
            <GridItem>
              <div className={classes.brand}>
                <h1 className={classes.title}>Service Application</h1>
                <h3 className={classes.subtitle}>HTL Grieskirchen</h3>
                <a href="/createService">
                <ServiceAddButton className={classes.button} color="primary">
                  <Link to="/createService">Neuen Service hinzufügen</Link>
                </ServiceAddButton>
                </a>

                  <a href="/showEmployees">
                  <OpenEmployeesButton
                  className={classes.button}
                  color="secondary"
                >
                    Mitarbeiter anzeigen
                </OpenEmployeesButton>
                  </a>
                {/*<OpenMapButton className={classes.button} color="primary">
                  Karte anzeigen
                </OpenMapButton>*/}
              </div>
            </GridItem>
          </GridContainer>
        </div>
      </Parallax>
<div className={classNames(classes.main, classes.mainRaised, ownStyles.mainRaised)} >

    {/*<SectionSearch/>*/}
    <SectionTable/>
    {/*<SectionFooter/>*/}

</div>
</div>
);
}