import React from "react";
import { Link } from "react-router-dom";

import IconWithText from "../IconWithText";
import "./style.css";

const CategoryCollection = () => {
  const category = [
    { id: 1, image: "cleaning.png", text: "청소", link: "/category/cleaning" },
    {
      id: 2,
      image: "bathroom.png",
      text: "화장실",
      link: "/category/bathroom",
    },
    {
      id: 3,
      image: "law.png",
      text: "법률",
      link: "/category/law",
    },
    {
      id: 4,
      image: "recycle.png",
      text: "분리수거",
      link: "/category/separateCollection",
    },
    {
      id: 5,
      image: "move.png",
      text: "이사",
      link: "/category/move",
    },
    {
      id: 6,
      image: "cook.png",
      text: "요리",
      link: "/category/cooking",
    },
    {
      id: 7,
      image: "pet.png",
      text: "애완견",
      link: "/category/pet",
    },
    /*{
      id: 8,
      image: "magnifying-glass.png",
      text: "???",
      link: "/category/question",
    },*/
  ];
  const categoryCollection = category.map((elem) => {
    const { id, image, text, link } = elem;
    return (
      <Link to={link} className="Menu" key={id}>
        <IconWithText image={image} text={text} />
      </Link>
    );
  });
  return (
    <div>
      <h3>카테고리 모아보기</h3>
      <div className="container">{categoryCollection}</div>
    </div>
  );
};

export default CategoryCollection;
