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
import OpenMapButton from "components/CustomButtons/OpenMapButton";
// sections for this page
import SectionBasics from "./Sections/SectionBasics.js";
import styles from "assets/jss/material-kit-react/views/components.js";

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
      <Parallax image={require("assets/img/bg7.jpg").default}>
        <div className={classes.container}>
          <GridContainer>
            <GridItem>
              <div className={classes.brand}>
                <h1 className={classes.title}>Service Application</h1>
                <h3 className={classes.subtitle}>HTL Grieskirchen</h3>
                <ServiceAddButton className={classes.button} color="primary">
                  Neuen Service hinzufügen
                </ServiceAddButton>
                <OpenEmployeesButton className={classes.button} color="warning">
                  Mitarbeiter anzeigen
                </OpenEmployeesButton>
                <OpenMapButton className={classes.button} color="primary">
                  Karte anzeigen
                </OpenMapButton>
              </div>
            </GridItem>
          </GridContainer>
        </div>
      </Parallax>
      <div className={classNames(classes.main, classes.mainRaised)}>
        <SectionBasics />
      </div>
    </div>
  );
}
