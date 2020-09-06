import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
//import logo from './logo.svg';
import "./App.css";

import HomePage from "./components/HomePage";
import MenuPage from "./components/MenuPage";
import WritingPage from "./components/WritingPage";
import MyPage from "./components/MyPage";
import Layout from "./components/Layout";
import InCategory from "./components/InCategory";

function App() {
  return (
    <Router className="App">
      <Switch>
        <Layout>
          <Route exact path="/" component={HomePage} />
          <Route path="/menu" component={MenuPage} />
          <Route path="/writing" component={WritingPage} />
          <Route path="/mypage" component={MyPage} />
          <Route path="/category/:key" component={InCategory} />
        </Layout>
      </Switch>
    </Router>
  );
}

export default App;
