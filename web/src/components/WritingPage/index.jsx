import React from 'react';
import { Link } from 'react-router-dom';

function WritingPage() {
  return (
    <Link to="/editor">
      <button>꿀팁 공유하러 가기</button>
    </Link>
  );
}

export default WritingPage;
