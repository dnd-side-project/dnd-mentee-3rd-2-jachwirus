import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';

import './style.css';

class Header extends Component {
  // componentDidMount() {
  //   console.log(this.props);
  // }

  goBack = () => {
    this.props.history.goBack();
  };

  render() {
    const curPath = this.props.curPath;
    let headerTitle = '';

    if (curPath === '/editor') {
      headerTitle = '꿀팁 공유';
      return (
        <header className="Ext-header">
          <a className="Header-left" onClick={this.goBack}>
            <img src="/images/close.png" />
          </a>
          {/* <h1 className="App-title">
            <Link to="/" className="Title-text">
              {headerTitle}
            </Link>
          </h1> */}
          <div className="App-title">{headerTitle}</div>
          <a className="Header-right">완료</a>
          <hr className="Header-bottom" />
        </header>
      );
    } else {
      headerTitle = '자취ers';
      return (
        <header>
          <h1 className="App-title">
            <Link to="/" className="Title-text">
              {headerTitle}
            </Link>
          </h1>
        </header>
      );
    }
  }
}

export default withRouter(Header);
