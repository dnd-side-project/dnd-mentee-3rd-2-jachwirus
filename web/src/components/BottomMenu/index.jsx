import React, { Component } from 'react';
import { Link } from 'react-router-dom';

import './style.css';
import IconWithText from '../IconWithText';

class BottomMenu extends Component {
  render() {
    const curPath = this.props.curPath;
    const { data } = this.props;
    const list = data.map((elem) => {
      const { id, link, img, txt } = elem;
      return (
        <Link to={link} className="Menu" key={id}>
          <IconWithText className="Image" image={img} text={txt} />
        </Link>
      );
    });

    if (curPath === '/editor') {
      return null;
    } else {
      return <nav className="MenuContainer">{list}</nav>;
    }
  }
}

export default BottomMenu;
