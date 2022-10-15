import signInReducer from './signInReducer';
import { combineReducers } from 'redux';

const rootReducer = combineReducers({ signInReducer });
export default rootReducer;
