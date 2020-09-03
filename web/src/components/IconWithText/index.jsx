import React from 'react';

import './style.css';

const IconWithText = ({ image, text }) => {
  return (
    <div>
      <img className="image" src={`/images/${image}`} alt={image} />
      <br />
      <div className="text">{text}</div>
    </div>
  );
};

export default IconWithText;
