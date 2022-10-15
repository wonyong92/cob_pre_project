import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEarthAmerica } from '@fortawesome/free-solid-svg-icons';

const StyledNav = styled.div`
  @import url('https://fonts.googleapis.com/css2?family=Roboto&display=swap');
  font-family: Roboto, sans-serif;
  color: #525960;
  display: flex;
  flex-direction: column;
  margin-top: 3px;
  min-height: 100vh;
  width: 164px;
  border-right: 1px solid #d2d2d2;
  a {
    text-decoration: none;
    color: #525960;
  }
  .home {
    margin-top: 30px;
    margin-bottom: 30px;
  }
  .category {
    font-size: 14px;
  }
  .menu {
    margin-top: 2px;
    display: flex;
    flex-direction: column;
    height: 120px;
    justify-content: space-between;
    align-items: flex-start;
    text-align: left;

    .question {
      width: 164px;
      padding: 10px;
      cursor: pointer;
    }
    .question:hover {
      background-color: #f1f2f3;
      border-right: 5px solid #f48225;
    }
  }
`;

function Nav() {
  return (
    <StyledNav>
      <Link to="/" className="home">
        Home
      </Link>
      <div className="category">PUBLIC</div>
      <div className="menu">
        <Link to="/" className="question">
          <FontAwesomeIcon icon={faEarthAmerica} />
          Questions
        </Link>
        <div className="question">Tags</div>
        <div className="question">Users</div>
        <div className="question">Companies</div>
      </div>
    </StyledNav>
  );
}

export default Nav;
