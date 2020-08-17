import React, { Component } from "react";
import { Link } from "react-router-dom";

import "./style.css";

class BottomMenu extends Component {
  render() {
    const { data } = this.props;
    const list = data.map((elem) => {
      const { link, img } = elem;
      return (
        <Link to={link} className="Menu">
          <img
            className="Image"
            src={require(`../../images/${img}`)}
            alt={img}
          />
        </Link>
      );
    });

    return <nav className="MenuContainer">{list}</nav>;
  }
}

export default BottomMenu;
