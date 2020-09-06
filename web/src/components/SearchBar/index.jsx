import React, { createRef, useState } from "react";
import "./style.css";

function SearchBar() {
  const [searchReference, setSearchReference] = useState(() => createRef());
  const [hashTagArray, setHashTagArray] = useState([]);
  const onKeyUp = (e) => {
    const word = searchReference.current.value;
    //console.dir(word);
    if (e.key === ",") {
      //단어를 박스로 만드세요.
      const parsedWord = "#" + word.slice(0, -1);
      setHashTagArray([...hashTagArray, parsedWord]); //값 있는지 검사
      searchReference.current.value = "";
      // console.log(hashTagArray);
    } else if (e.key === "Enter") {
      //해시태그를 모아서 검색 요청을 보내세요.
    }
  };
  console.log(hashTagArray);
  return (
    <div>
      {hashTagArray}
      <form
        action="/find_process"
        method="post"
        onSubmit={function (e) {
          e.preventDefault();
        }}
        className="Container"
      >
        <input
          className="SearchButton"
          type="image"
          src="/images/magnifying-glass.png"
          alt="submit"
        />

        <input
          className="Search"
          type="text"
          name="search"
          placeholder="궁금한 점을 해시태그로 검색하세요."
          onKeyUp={onKeyUp}
          ref={searchReference}
        />
      </form>
    </div>
  );
}

export default SearchBar;
