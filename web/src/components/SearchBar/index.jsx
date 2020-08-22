import React from "react";
import findImage from "../../images/magnifying-glass.png";
import "./style.css";

function SearchBar() {
  return (
    <form
      action="/find_process"
      method="post"
      onSubmit={function (e) {
        e.preventDefault();
        //debugger;
        // this.props.onSubmit(
        //     e.target.find.value,
        //     e.target.desc.value
        // );
      }}
      //}.bind(this)}
      className="Container"
    >
      <input
        className="SearchButton"
        type="image"
        src={findImage}
        alt="submit"
      ></input>
      <input
        className="Search"
        type="text"
        name="search"
        placeholder="궁금한 점을 해시태그로 검색하세요."
      ></input>
    </form>
  );
}

export default SearchBar;
