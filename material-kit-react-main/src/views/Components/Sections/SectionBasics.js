import React from "react";
import Button from "components/CustomButtons/Button.js";

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
        </div>
      </div>
    </div>
  );
}
