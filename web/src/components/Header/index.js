import React, {Component} from 'react';
import './index.css';

class Header extends Component {
    render() {
      return (
        <header>
          <h1><a href="/" onClick={function(e){
              e.preventDefault();
              this.props.onChangePage();
          }.bind(this)} className="App-title">{this.props.title}</a></h1>
        </header>
      );
    }
  }

export default Header;