import React from 'react';
import './style.css';
import 'codemirror/lib/codemirror.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import { Editor } from '@toast-ui/react-editor';

// class WritingPage extends Component {
//   render() {
//     return (
//       <header>
//         <h1>Writing Page</h1>
//       </header>
//     );
//   }
// }

function WritingPost() {
  return (
    <form>
      <div className="InputDiv" style={{ top: 138 }}>
        <span className="InputTitle">태그 입력 : </span>
        <input type="text" className="InputText" />
      </div>
      <div className="InputDiv">
        <span className="InputTitle">제목 : </span>
        <input type="text" className="InputText" />
      </div>
      <Editor
        initialValue="당신의 꿀팁을 작성해주세요!"
        previewStyle="vertical"
        height="410px"
        initialEditType="wysiwyg"
        language="ko_KR"
        useCommandShortcut={true}
      />
    </form>
  );
}

export default WritingPost;
