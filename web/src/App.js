import React from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
//import logo from './logo.svg';
import "./App.css";

import HomePage from "./components/HomePage";
import MenuPage from "./components/MenuPage";
import WritingPage from "./components/WritingPage";
import MyPage from "./components/MyPage";
import Layout from "./components/Layout";

function App() {
  return (
    <Router className="App">
      <Switch>
        <Layout>
          <Route exact path="/">
            <HomePage />
          </Route>
          <Route exact path="/menu">
            <MenuPage />
          </Route>
          <Route exact path="/writing">
            <WritingPage />
          </Route>
          <Route exact path="/mypage">
            <MyPage />
          </Route>
        </Layout>
      </Switch>
    </Router>
  );
}

export default App;
