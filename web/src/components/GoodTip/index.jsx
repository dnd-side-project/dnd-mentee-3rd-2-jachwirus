import React from "react";

import HashTag from "../HashTag";
import "./style.css";

const GoodTip = (props) => {
  const data = props.posts;
  const list = data.map((elem) => {
    const { id, title, tag } = elem;
    return (
      <li className="Post" key={id}>
        <HashTag tag={tag} />
        {title}
      </li>
    );
  });
  return (
    <div className="GoodTip">
      <h3>{props.title}</h3>
      <div className="List">{list}</div>
    </div>
  );
};

export default GoodTip;
