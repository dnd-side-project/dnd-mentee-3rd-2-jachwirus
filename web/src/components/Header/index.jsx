import React, { Component } from "react";
import { Link } from "react-router-dom";

import "./style.css";

class Header extends Component {
  render() {
    return (
      <header id="headerContainer">
        <div className="App-title">
          <Link to="/" className="Title-text">
            <img id="logo" src="/images/logo.png" alt="logo" />
          </Link>
        </div>
        {/*<div id="headerNotice">
          <img
            id="headerNoticeIcon"
            src="/images/notice.png"
            alt="headerNoticeIcon"
          />
        </div>*/}
      </header>
    );
  }
}

export default Header;
