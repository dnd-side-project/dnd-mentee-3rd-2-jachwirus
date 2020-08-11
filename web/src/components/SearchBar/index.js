import React from 'react';
import findImage from '../../images/magnifying-glass.png';
import './index.css'

function SearchBar() {
  return (
    <form action="/find_process" method="post"
    onSubmit={function(e){
        e.preventDefault();
        //debugger;
        // this.props.onSubmit(
        //     e.target.find.value,
        //     e.target.desc.value
        // );
    }.bind(this)}
    className="Container"
    >
    <input className="Search" type="text" name="search" placeholder="해시태그 검색"></input>
    <input className="SearchButton" type="image" src={findImage} alt="submit"></input>
    </form>
  );
}

export default SearchBar;