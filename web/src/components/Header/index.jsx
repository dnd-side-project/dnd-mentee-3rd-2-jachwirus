import React, { Component } from "react";
import "./style.css";

class Header extends Component {
  render() {
    return (
      <header>
        <h1 className="App-title">
          <a
            href="/"
            onClick={function (e) {
              e.preventDefault();
              this.props.onChangePage();
            }.bind(this)}
            className="Title-text"
          >
            {this.props.title}
          </a>
        </h1>
      </header>
    );
  }
}

export default Header;
