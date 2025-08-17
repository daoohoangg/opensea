// tailwind.config.js
export const content = [
    './src/**/*.{html,js,jsx,ts,tsx}',
    './public/index.html',
];
export const theme = {
    extend: {},
};
export const plugins = [];
// tailwind.config.js
module.exports = {
  theme: {
    extend: {
      screens: {
        '3xl': '1920px',   // màn hình lớn hơn 1920px
        '4xl': '3000px',   // màn hình 2K / 4K
      },
    },
  },
}
