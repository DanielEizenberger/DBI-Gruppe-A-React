import React from "react";
import Button from "components/CustomButtons/Button.js";
import GridContainer from "components/Grid/GridContainer.js";
import GridItem from "components/Grid/GridItem.js";

export default function SectionBasics() {
  React.useEffect(() => {});

  return (
    <div>
      <div>
        <div>
          <h2>Basic Elements</h2>
          <Button type="button" color="primary">
            Click if u hate Prettier/Prettier
          </Button>
          <GridContainer>
            <GridItem>jo</GridItem>
            <GridItem>jo</GridItem>
            <GridItem>jo</GridItem>
            <GridItem>jo</GridItem>
          </GridContainer>
        </div>
      </div>
    </div>
  );
}
