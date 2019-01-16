<template>
  <div class="Expander">
    <div class="Expander__trigger"
         @click="open=!open"
         :class="open?'active':'beforeBorder'">
      <svg
        class="Expander__trigger-Icon"
        :class="{open:open}"
        width="40" height="12"
        stroke="cornflowerblue">
        <polyline points="12,2 20,10 28,2" stroke-width="3" fill="none"></polyline>
      </svg>
      {{ title }}
    </div>
    <transition :name="animation">
      <div class="Expander__body" v-show="open">
        <slot></slot>
      </div>
    </transition>
  </div>
</template>

<script>
  export default {
    props: {
      title: {
        type: String,
        default: 'title'
      },
      animation: {
        type: String,
        default: 'rightToLeft'
      }
    },
    data() {
      return {
        open: false
      }
    }
  }
</script>

<style scoped>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }

  body {
    font: 100%/1.6 'Source Sans Pro', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;
    color: #444;
    -moz-osx-font-smoothing: grayscale;
    -webkit-font-smoothing: antialiased;
  }

  .Card {
    padding: 1em;
    margin: 1rem;
    box-shadow: 0 1px 8px rgba(0, 0, 0, 0.25);
  }

  .Wrap {
    padding: 1em;
    margin: auto;
    max-width: 1200px;
  }

  a,
  .link {
    color: #477dca;
  }

  .link {
    text-decoration: underline;
  }

  .Tag {
    font-family: monospace;
    background: #f0f8ff;
    padding: 0.3em;
    margin: 1em 0;
  }

  .Tag:before {
    content: '[ ';
  }

  .Tag:after {
    content: ' ] ';
  }

  .Tag:before,
  .Tag:after {
    color: #00f;
  }

  .H1,
  h1,
  h2,
  h3,
  h4,
  h5 {
    line-height: 1;
    margin: 0 0 1em;
  }

  h1,
  h2 {
    color: #3c4858;
    font-weight: 400;
    -moz-osx-font-smoothing: grayscale;
    -webkit-font-smoothing: antialiased;
  }

  p {
    margin-bottom: 0.75em;
  }

  .col {
    flex: 1;
    margin-left: 1em;
  }

  @media (min-width: 600px) {
    .flex-row {
      display: flex;
      flex-wrap: wrap;
    }

    .w_25 {
      flex: 2.5;
    }

    .w_75 {
      flex: 7.5;
    }

    .w_50 {
      flex: 5;
    }
  }

  .form-group {
    display: flex;
    flex-direction: column;
  }

  .form-group > * {
    width: 240px;
    margin-bottom: 1em;
  }

  .checked-group label {
    display: block;
  }

  .form-line {
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    margin: -0.3em;
  }

  .form-line + .form-line {
    margin-top: 0.7em;
  }

  .form-line .btn,
  .form-line button,
  .form-line input,
  .form-line select {
    margin: 0.35em;
  }

  .form-line label {
    white-space: nowrap;
    padding-right: 0.7em;
  }

  fieldset {
    border: 1px solid #dee2e6;
    padding: 1em 1.4em 1.4em;
    margin: 1em 0;
  }

  legend {
    padding: 0 0.3em;
  }

  label {
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    cursor: pointer;
    white-space: nowrap;
    display: inline-table;
  }

  .button-group {
    display: inline-flex;
    margin: 0.35em;
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    border-radius: 0.3em;
  }

  .button-group .btn {
    height: 100%;
    border-color: transparent;
    box-shadow: none;
    border-radius: 0;
  }

  .button-group .btn:first-child {
    border-radius: 0.3em 0 0 0.3em;
  }

  .button-group .btn:last-child {
    border-radius: 0 0.3em 0.3em 0;
  }

  .button-group .btn + .btn {
    border-left: 1px solid #dee2e6;
  }

  input[type="color"],
  input[type="range"] {
    vertical-align: middle;
  }

  input[type="color"] {
    height: 3em;
    background: transparent;
    border: 0;
    cursor: pointer;
  }

  input[type="file"] {
    background-color: #fff;
  }

  button::-moz-focus-inner {
    border: 0;
  }

  select:not([multiple]):-moz-focusring {
    color: transparent;
    text-shadow: 0 0 0 #000;
  }

  .disabled,
  [disabled] {
    pointer-events: none;
    opacity: 0.5;
  }

  input,
  .btn,
  button,
  select,
  textarea {
    font-size: 0.8rem;
    outline: 0;
  }

  input.sm,
  .btn.sm,
  button.sm,
  select.sm,
  textarea.sm {
    font-size: 0.6rem;
  }

  input.lg,
  .btn.lg,
  button.lg,
  select.lg,
  textarea.lg {
    font-size: 1rem;
  }

  input.xl,
  .btn.xl,
  button.xl,
  select.xl,
  textarea.xl {
    font-size: 1.2rem;
  }

  input:not([type="range"]):not([type="color"]),
  select,
  textarea {
    box-shadow: 0 4px 6px rgba(50, 50, 93, 0.11), 0 1px 3px rgba(0, 0, 0, 0.08);
  }

  input:not([type=color]):not([type=range]):not([type=radio]):not([type=checkbox]),
  .select,
  select:not([multiple]),
  textarea,
  .btn,
  button {
    line-height: 1.15;
    padding: 0.6em 1em;
    border-radius: 0.3em;
  }

  input:not([type=color]):not([type=range]):not([type=radio]):not([type=checkbox]),
  .select,
  select,
  textarea {
    border: 1px solid #bdc3c7;
  }

  input:not([type=color]):not([type=range]):not([type=radio]):not([type=checkbox]):focus,
  .select:focus,
  select:focus,
  textarea:focus {
    border-radius: 0;
    border-top-color: #477dca;
  }

  .btn {
    display: inline-block;
    white-space: nowrap;
    text-decoration: none;
    vertical-align: middle;
  }

  .btn,
  button {
    box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12);
    border: 1px solid transparent;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
    transition: background 0.3s cubic-bezier(.54, .05, .07, .87);;
    background: #3498db;
    color: #fff;
  }

  .btn:active,
  button:active {
    -webkit-transform: translateY(2px);
    transform: translateY(2px);
  }

  .btn:hover,
  button:hover {
    background: #48acef;
  }

  .btn.fill.lite {
    color: #477dca;
    background: #fffafa;
  }

  .btn.fill.lite:hover {
    text-decoration: underline;
  }

  .btn.fill.red {
    background: #ed5565;
  }

  .btn.fill.red:hover {
    background: #f17784;
  }

  .btn.fill.green {
    background: #26a69a;
  }

  .btn.fill.green:hover {
    background: #3abaae;
  }

  .btn.fill.orange {
    background: #ff9800;
  }

  .btn.fill.orange:hover {
    background: #ffad33;
  }

  .btn.flat {
    background-color: transparent;
    box-shadow: none;
    color: #444;
    text-transform: uppercase;
    font-weight: 500;
  }

  .btn.flat:hover {
    background: rgba(119, 119, 119, 0.2);
  }

  .btn.flat.blue {
    color: #3498db;
  }

  .btn.flat.blue:hover {
    background: rgba(52, 152, 219, 0.2);
  }

  .btn.flat.red {
    color: #ed5565;
  }

  .btn.flat.red:hover {
    background: rgba(237, 85, 101, 0.2);
  }

  .btn.flat.green {
    color: #26a69a;
  }

  .btn.flat.green:hover {
    background: rgba(38, 166, 154, 0.2);
  }

  .btn.flat.orange {
    color: #ff9800;
  }

  .btn.flat.orange:hover {
    background: rgba(255, 152, 0, 0.2);
  }

  .btn.outline {
    border: 2px solid currentColor;
    border-radius: 2em;
    transition: none;
    background: #fffafa;
    color: #999;
  }

  .btn.outline:hover {
    color: #fff !important;
    border-color: transparent;
    background: #9e9e9e;
  }

  .btn.outline.blue {
    color: #3498db;
  }

  .btn.outline.blue:hover {
    background: #3498db;
  }

  .btn.outline.red {
    color: #ed5565;
  }

  .btn.outline.red:hover {
    background: #ed5565;
  }

  .btn.outline.green {
    color: #26a69a;
  }

  .btn.outline.green:hover {
    background: #26a69a;
  }

  .btn.outline.orange {
    color: #ff9800;
  }

  .btn.outline.orange:hover {
    background: #ff9800;
  }

  .btn.sm {
    font-size: 0.6rem;
  }

  .btn.lg {
    font-size: 1rem;
  }

  .btn.xl {
    font-size: 1.2rem;
  }

  select:not([multiple]),
  .select {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    cursor: pointer;
    background: #fff url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' width='22' height='12'><polygon points='0,0 12,0 6,12' fill='orange'/></svg>") 100% no-repeat;
    padding-right: 30px;
  }

  select[multiple] {
    cursor: pointer;
    padding: 0.2em;
  }

  select[multiple] option {
    padding: 0.2em;
    padding-left: 0.8em;
  }

  select[multiple] option:first-child {
    background: #efefef;
  }

  input[type="checkbox"],
  input[type="radio"] {
    padding: 0;
    border: 2px solid #2196f3;
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
    cursor: pointer;
    height: 20px;
    width: 20px !important;
    vertical-align: sub;
    margin: 0 0.5em;
  }

  input[type="checkbox"]:checked,
  input[type="radio"]:checked {
    background: #2196f3;
    -webkit-animation: bounce 0.3s;
    animation: bounce 0.3s;
  }

  input[type=checkbox] {
    border-radius: 0.2em;
  }

  input[type=checkbox]:checked {
    background-image: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16'><path fill='none' stroke-width='3' stroke='white' d='M3,7 L7,11 L13,4'/></svg>");
  }

  input[type=radio] {
    border-radius: 50%;
  }

  input[type=radio]:checked {
    background-image: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 16 16'><circle cx='8' cy='8' r='4' fill='white'/></svg>");
  }

  .table-responsive {
    width: 100%;
    overflow-x: auto;
  }

  table {
    border-collapse: collapse;
    width: 100%;
    margin-bottom: 2em;
  }

  tr {
    border-bottom: 1px solid #dee2e6;
  }

  th {
    text-align: left;
    border-bottom: 3px solid #dee2e6;
  }

  td,
  th {
    padding: 0.5rem;
  }

  .td_buttonGroup {
    width: 1%;
  }

  .table_distance td,
  .table_distance th {
    padding: 1.2rem 1.5rem;
  }

  .table_striped tbody tr:nth-of-type(odd) {
    background-color: rgba(0, 0, 0, 0.05);
  }

  nav a,
  .list-Card li,
  .btn,
  button {
    position: relative;
    overflow: hidden;
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }

  nav a:after,
  .list-Card li:after,
  .btn:after,
  button:after {
    content: "";
    display: block;
    position: absolute;
    width: 100%;
    height: 100%;
    top: 0;
    left: 0;
    pointer-events: none;
    background-image: radial-gradient(circle, #000 10%, transparent 10.01%);
    background-repeat: no-repeat;
    background-position: 50%;
    -webkit-transform: scale(10, 10);
    transform: scale(10, 10);
    opacity: 0;
    transition: opacity 1s, -webkit-transform 0.5s;
    transition: transform 0.5s, opacity 1s;
    transition: transform 0.5s, opacity 1s, -webkit-transform 0.5s;
  }

  nav a:active:after,
  .list-Card li:active:after,
  .btn:active:after,
  button:active:after {
    -webkit-transform: scale(0, 0);
    transform: scale(0, 0);
    opacity: 0.2;
    transition: 0s;
  }

  .beforeBorder {
    position: relative;
  }

  .beforeBorder:before {
    transition: opacity 0.1s linear, -webkit-transform 0.5s cubic-bezier(.54, .05, .07, .87);
    transition: opacity 0.1s linear, transform 0.5s cubic-bezier(.54, .05, .07, .87);
    transition: opacity 0.1s linear, transform 0.5s cubic-bezier(.54, .05, .07, .87), -webkit-transform 0.5s cubic-bezier(.54, .05, .07, .87);;
    position: absolute;
    border-bottom: 1px solid currentColor;
    content: '';
    width: 100%;
    left: 0;
    bottom: -1px;
  }

  .beforeBorder:not(:hover)::before {
    -webkit-transform: scaleX(0);
    transform: scaleX(0);
    opacity: 0;
  }

  .Accordion {
    background: #fff;
    box-shadow: 0 1px 12px 1PX rgba(0, 0, 0, 0.25);
    overflow: hidden;
  }

  .Expander__trigger {
    cursor: pointer;
    padding: 0.7rem 0.5rem;
    border-bottom: 1px solid #efefef;
  }

  .Expander__trigger:hover {
    color: #477dca;
  }

  .Expander__trigger.active {
    border-bottom-color: #477dca;
  }

  .Expander__trigger-Icon {
    transition: -webkit-transform 0.2s cubic-bezier(0.23, 1, 0.32, 1);
    transition: transform 0.2s cubic-bezier(0.23, 1, 0.32, 1);
    transition: transform 0.2s cubic-bezier(0.23, 1, 0.32, 1), -webkit-transform 0.2s cubic-bezier(0.23, 1, 0.32, 1);
  }

  .Expander__trigger-Icon.open {
    stroke: #ff6347;
    -webkit-transform: rotate(180deg);
    transform: rotate(180deg);
  }

  .Expander__body {
    padding: 1rem 1.5rem;
    background: #fff;
  }

  .rightToLeft-enter-active {
    -webkit-animation: rightToLeft 0.5s;
    animation: rightToLeft 0.5s;
  }

  .rightToLeft-leave-active {
    animation: rightToLeft 0.5s reverse;
  }

  .leftToRight-enter-active {
    -webkit-animation: leftToRight 0.5s;
    animation: leftToRight 0.5s;
  }

  .leftToRight-leave-active {
    animation: leftToRight 0.5s reverse;
  }

  .bounceIn-enter-active {
    -webkit-animation: bounceIn 0.3s;
    animation: bounceIn 0.3s;
  }

  .bounceIn-leave-active {
    animation: bottomToTop 0.2s reverse;
  }

  .bottomToTop-enter-active {
    -webkit-animation: bottomToTop 0.5s forwards;
    animation: bottomToTop 0.5s forwards;
  }

  .bottomToTop-leave-active {
    animation: bottomToTop 0.5s reverse;
  }

  @-webkit-keyframes bounce {
    0%, 100% {
      -webkit-transform: scale(1);
      transform: scale(1);
    }
    50% {
      -webkit-transform: scale(0.7);
      transform: scale(0.7);
    }
  }

  @keyframes bounce {
    0%, 100% {
      -webkit-transform: scale(1);
      transform: scale(1);
    }
    50% {
      -webkit-transform: scale(0.7);
      transform: scale(0.7);
    }
  }

  @-webkit-keyframes rightToLeft {
    0% {
      -webkit-transform: translateX(100vw);
      transform: translateX(100vw);
    }
    50% {
      -webkit-transform: translateX(-2em);
      transform: translateX(-2em);
    }
    100% {
      -webkit-transform: translateX(0);
      transform: translateX(0);
    }
  }

  @keyframes rightToLeft {
    0% {
      -webkit-transform: translateX(100vw);
      transform: translateX(100vw);
    }
    50% {
      -webkit-transform: translateX(-2em);
      transform: translateX(-2em);
    }
    100% {
      -webkit-transform: translateX(0);
      transform: translateX(0);
    }
  }

  @-webkit-keyframes leftToRight {
    0% {
      -webkit-transform: translateX(-100vw);
      transform: translateX(-100vw);
    }
    50% {
      -webkit-transform: translateX(2em);
      transform: translateX(2em);
    }
    100% {
      -webkit-transform: translateX(0);
      transform: translateX(0);
    }
  }

  @keyframes leftToRight {
    0% {
      -webkit-transform: translateX(-100vw);
      transform: translateX(-100vw);
    }
    50% {
      -webkit-transform: translateX(2em);
      transform: translateX(2em);
    }
    100% {
      -webkit-transform: translateX(0);
      transform: translateX(0);
    }
  }

  @-webkit-keyframes bounceIn {
    from, 20%, 40%, 60%, 80%, to {
      -webkit-animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
      animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
    }
    0% {
      opacity: 0;
      -webkit-transform: scale3d(0.3, 0.3, 0.3);
      transform: scale3d(0.3, 0.3, 0.3);
    }
    20% {
      -webkit-transform: scale3d(1.1, 1.1, 1.1);
      transform: scale3d(1.1, 1.1, 1.1);
    }
    40% {
      -webkit-transform: scale3d(0.9, 0.9, 0.9);
      transform: scale3d(0.9, 0.9, 0.9);
    }
    60% {
      opacity: 1;
      -webkit-transform: scale3d(1.03, 1.03, 1.03);
      transform: scale3d(1.03, 1.03, 1.03);
    }
    80% {
      -webkit-transform: scale3d(0.97, 0.97, 0.97);
      transform: scale3d(0.97, 0.97, 0.97);
    }
    to {
      opacity: 1;
      -webkit-transform: scale3d(1, 1, 1);
      transform: scale3d(1, 1, 1);
    }
  }

  @keyframes bounceIn {
    from, 20%, 40%, 60%, 80%, to {
      -webkit-animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
      animation-timing-function: cubic-bezier(0.215, 0.61, 0.355, 1);
    }
    0% {
      opacity: 0;
      -webkit-transform: scale3d(0.3, 0.3, 0.3);
      transform: scale3d(0.3, 0.3, 0.3);
    }
    20% {
      -webkit-transform: scale3d(1.1, 1.1, 1.1);
      transform: scale3d(1.1, 1.1, 1.1);
    }
    40% {
      -webkit-transform: scale3d(0.9, 0.9, 0.9);
      transform: scale3d(0.9, 0.9, 0.9);
    }
    60% {
      opacity: 1;
      -webkit-transform: scale3d(1.03, 1.03, 1.03);
      transform: scale3d(1.03, 1.03, 1.03);
    }
    80% {
      -webkit-transform: scale3d(0.97, 0.97, 0.97);
      transform: scale3d(0.97, 0.97, 0.97);
    }
    to {
      opacity: 1;
      -webkit-transform: scale3d(1, 1, 1);
      transform: scale3d(1, 1, 1);
    }
  }

  @-webkit-keyframes bottomToTop {
    0% {
      opacity: 0;
      -webkit-transform: translateY(100%);
      transform: translateY(100%);
    }
    100% {
      -webkit-transform: translateY(0);
      transform: translateY(0);
    }
  }

  @keyframes bottomToTop {
    0% {
      opacity: 0;
      -webkit-transform: translateY(100%);
      transform: translateY(100%);
    }
    100% {
      -webkit-transform: translateY(0);
      transform: translateY(0);
    }
  }

</style>
