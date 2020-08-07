import React, {Component} from 'react';
import './index.css';

class BottomMenu extends Component {
    render() {
        var lists = []
        var data = this.props.data;
        var i = 0;
        while(i < data.length) {
            lists.push(
                <button 
                  className="Menu"
                  key={data[i].id}
                  onClick={function(id, e){
                      e.preventDefault();
                      this.props.onChangePage(id);
                  }.bind(this, data[i].id)}
                ><img className="Image" src={require(`../../images/${data[i].img}`)} /></button>)
            i = i + 1;
        }
      return (
        <nav className="MenuContainer">
          {lists}
        </nav>
      );
    }
  }

export default BottomMenu;