import React, { Component } from "react";
import { Link } from "react-router-dom";

import "./style.css";

class Header extends Component {
  render() {
    return (
      <header>
        <h1 className="App-title">
          <Link to="/" className="Title-text">
            {this.props.title}
          </Link>
        </h1>
      </header>
    );
  }
}

export default Header;
