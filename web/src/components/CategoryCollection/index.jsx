import React from "react";

import IconWithText from "../IconWithText";
import "./style.css";

const CategoryCollection = () => {
  const category = [
    { id: 1, image: "house.png", text: "청소" },
    {
      id: 2,
      image: "magnifying-glass.png",
      text: "화장실",
    },
    {
      id: 3,
      image: "avatar.png",
      text: "법률",
    },
    {
      id: 4,
      image: "edit.png",
      text: "분리수거",
    },
    {
      id: 5,
      image: "house.png",
      text: "이사",
    },
    {
      id: 6,
      image: "menu.png",
      text: "요리",
    },
    {
      id: 7,
      image: "house.png",
      text: "애완견",
    },
    {
      id: 8,
      image: "magnifying-glass.png",
      text: "???",
    },
  ];
  const categoryCollection = category.map((elem) => {
    const { id, image, text } = elem;
    return <IconWithText image={image} text={text} key={id} />;
  });
  return (
    <div>
      <h3>카테고리 모아보기</h3>
      <div className="container">{categoryCollection}</div>
    </div>
  );
};

export default CategoryCollection;
