const path = require('path');

module.exports = {
    entry: './src/frontend/src/index.js', // Assuming your main frontend entry point is index.js
    devtool: 'sourcemaps',
    cache: true,
    mode: 'development', // Or 'production' for optimized builds
    output: {
        path: path.resolve(__dirname, 'src/main/resources/static/built'), // Output to static directory
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/, // Include JS and JSX files
                exclude: /(node_modules)/,
                use: {
                    loader: 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env', '@babel/preset-react']
                    }
                }
            },
            {
                test: /\.css$/, // Add a rule for CSS (optional)
                use: ['style-loader', 'css-loader']
            }
        ]
    }
};
